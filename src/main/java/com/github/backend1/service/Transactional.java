package com.github.backend1.service;

public @interface Transactional {
    boolean readOnly();