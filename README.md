# Spring Batch Study

## 용어
 - **job**: 배치가 실행되는 작업 단위
 - **step**: job 내부에서 실행되는 로직 단위
 - **tasklet**: step 내부 구현 (통합)
 - **reader**: 배치 실행을 위해 필요한 데이터를 읽어오는 작업
 - **processor**: reader가 읽어온 데이터로 비지니스 로직을 실행하는 작업
 - **writer**: processor가 처리한 데이터를 저장하는 작업

## 테이블 스키마

### batch_job_instance
job parameter에 따라 데이터 추가
파라매터가 같으면 데이터가 추가되지 않음

### batch_job_execution
프로세스가 실행하는 job의 상태
`batch_job_instance`와 1:n 관계

### batch_job_execution_param
`batch_job_execution`가 사용하는 파라메터 값

