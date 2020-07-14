/*! Copyright (c) 2020 LSWare Inc. All rights reserved */
package dev.redpanda.spring.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling // 스케쥴링 활성화
@EnableBatchProcessing // 배치 어플리케이션 활성화
@SpringBootApplication
class Main

fun main() {
  runApplication<Main>()
}
