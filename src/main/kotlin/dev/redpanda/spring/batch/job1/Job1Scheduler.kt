/*! Copyright (c) 2020 LSWare Inc. All rights reserved */
package dev.redpanda.spring.batch.job1

import dev.redpanda.spring.batch.Job.JOB1
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import kotlin.random.Random

/**
 * 100초마다 job1을 실행하는 스케쥴러
 */
@Component
class Job1Scheduler(
  private val launcher: JobLauncher,
  private val job1: Job
) {

  @Scheduled(fixedDelay = 1 * 1000L) // 1초
  fun job1Schedule () {
    println("[$JOB1] 시작 (${now()})")

    val seed = Random.nextLong(0, Long.MAX_VALUE)
    println("Seed: $seed")

    // 파라매터 없음
    val parameter = JobParametersBuilder()
      .addLong("seed", seed)
      .toJobParameters()

    val execution = launcher.run(job1, parameter)

    println("[$JOB1] ${execution.status}: (${execution.endTime})")
  }
}
