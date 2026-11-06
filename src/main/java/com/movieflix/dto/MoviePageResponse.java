package com.movieflix.dto;

import java.util.List;

public record MoviePageResponse(List<MovieDto> movieDtos, Integer pageNumber, Integer pageSize, long totalElement, long totalPages, boolean isLast){ 

}
