package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.ListInSpaceApi;

@Service
public class ListinSpaceService {
	@Autowired
	ListInSpaceApi listInSpaceApi;
}
