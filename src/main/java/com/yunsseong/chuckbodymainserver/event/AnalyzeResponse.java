package com.yunsseong.chuckbodymainserver.event;

import java.util.List;

public record AnalyzeResponse(String summary, List<String> tags, String origin_text, Boolean success) {
}
