package org.ezcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * MemberController
 */
@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {

    @GetMapping("/all")
    public void doAll() {
        log.info("all...........");
    }

    @GetMapping("/member")
    public void doMember() {
        log.info("member...........");
    }

    @GetMapping("/admin")
    public void doAdmin() {
        log.info("admin...........");
    }

    
}