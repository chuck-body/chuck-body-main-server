package com.yunsseong.chuckbodymainserver.event;

import com.yunsseong.chuckbodymainserver.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsultationEventListener {

    private final ConsultationRepository consultationRepository;
    private final RestTemplate restTemplate;

    @Value("${AI_SERVER_URL}")
    private String url;

    @Async
    @EventListener
    public void handleConsultationCreated(ConsultationCreatedEvent event) {
        try {
            log.info("Processing Consultation {}", event.consultationId());

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("audio_file", new ByteArrayResource(event.voiceFile()) {
                @Override
                public String getFilename() {
                    return UUID.randomUUID() + "." + event.extension();
                }
            });

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .path("/api/v1/audio-to-tags")
                    .queryParam("num_speakers", event.speakerNumber());
            URI uri = builder.build().encode().toUri();

            ResponseEntity<AnalyzeResponse> response = restTemplate.postForEntity(
                    uri,
                    requestEntity,
                    AnalyzeResponse.class
            );

            AnalyzeResponse result = response.getBody();

            consultationRepository.findById(event.consultationId())
                    .ifPresent(c -> {
                        c.setSummary(result.summary());
                        c.setTags(String.join(",", result.tags()));
                        c.setConversation(result.origin_text());
                        consultationRepository.save(c);
                    });

            log.info("Consultation {} processing done", event.consultationId());
        } catch (Exception e) {
            log.error("Error processing Consultation {}", event.consultationId(), e);
        }
    }
}
