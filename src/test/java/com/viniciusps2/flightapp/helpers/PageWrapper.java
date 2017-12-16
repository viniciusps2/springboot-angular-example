package com.viniciusps2.flightapp.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageWrapper<T> {
    private final List<T> content;

    public PageWrapper(List<T> content) {
        this.content = content;
    }

    public Page<T> getPage () {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        PageRequest pageRequest = new PageRequest(1, 10, sort);
        return new PageImpl<T>(content, pageRequest, content.size());
    }
}
