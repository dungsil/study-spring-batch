package dev.redpanda.spring.batch.job1

import dev.redpanda.spring.batch.JobName.JOB1
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus.FINISHED
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 생성된 step과 job을 설정하는 클래스
 */
@Configuration
class Job1BatchConfiguration(
  private val jobBuilderFactory: JobBuilderFactory,
  private val stepBuilderFactory: StepBuilderFactory
) {

  /**
   * 잡 설정
   *
   * 잡에는 1개 이상의 step이 포함될 수 잇음
   */
  @Bean
  fun job1(): Job = jobBuilderFactory.get(JOB1) // [JOB1]이란 이름을 가진 job 생성
    .start(step1(null)) // [step1]을 시작 step으로 등록
    .build()

  /**
   * 잡의 작업 단위
   * return@tasklet이 FINISHED이면 작업이 완료되고 CONTINUABLE이면 해당 스텝을 재시도한다.
   *
   */
  @Bean
  @JobScope // jobParameters를 받는 경우 사용해줘야함 (bean을 singleton에서 lazy load로 바꾸는 역할)
  fun step1(@Value("#{jobParameters[seed]}") seed: Long?): Step = stepBuilderFactory.get("$JOB1-step")
    .tasklet { _, _ -> // step 내부에서 수행 될 기능
      println(">> $JOB1-step (seed: $seed)")
      return@tasklet FINISHED
    }
    .build()
}
