# android-deep-dive

- [Lifecycle](https://github.com/JisangYou/android-deep-dive/tree/master/LifeCycle)
- [Coroutine](https://github.com/JisangYou/android-deep-dive/tree/master/Coroutine)
- [ConstraintLayout](https://github.com/JisangYou/android-deep-dive/tree/master/ConstraintLayout)
- [Proguard](https://github.com/JisangYou/android-deep-dive/tree/master/Proguard)
- [Compose](https://github.com/JisangYou/android-deep-dive/tree/master/Compose)


## 목차

- AAC

- Gradle이란?

- Android KTX란?

- 코루틴 

```
Android KTX는 Android Jetpack과 기타 Android 라이브러리에 포함된 Kotlin 확장 프로그램 세트입니다. KTX 확장 프로그램은 간결하고 직관적인 Kotlin을 Jetpack, Android 플랫폼, 기타 API에 제공합니다. 이렇게 하기 위해 이러한 확장 프로그램은 다음과 같은 여러 Kotlin 언어 기능을 활용합니다.

확장 함수
확장 프로그램 속성
람다
이름이 지정된 매개변수
매개변수 기본값
코루틴
```

-about ViewModel
```
- ViewModel은 액티비티와 프래그먼트에서 사용되는 UI 관련 데이터를 보관, 관리하기 위해 디자인
- 액티비티가 재생성 되는 상황에서도 ViewModel 인스턴스를 유지함으로써 데이터를 안전하게 다룰 수 있음
- 데이터의 소유권을 액티비티와 프래그먼트로부터 분리시킴으로써 관심사 분리, 즉 액티비티와 프래그먼트는 UI를 업데이트하는 역할에 집중시킨다는 의미에서 단일 책임 원칙을 따를 수 있는 발판이 마련덕분에 UI 로직에 대한 테스트도 수월
```
[출처](https://medium.com/@jungil.han/%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8-viewmodel-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-2e4d136d28d2)

- ViewModel vs AndroidViewModel

```
액티비티가 재생성 될 때, ViewModel은 액티비티 수명주기 외부에 존재하기 때문에 UI 컨텍스트를 ViewModel에 저장한다면 메모리 릭을 발생시키는 직접적인 원인이 될 수 있습니다. 
다만 Application 컨택스트를 저장하는 것은 문제가 되지 않습니다. Application 컨택스트는 전체 앱의 수명주기를 의미하기 때문에 메모리 릭에 영향을 주지 않으며 이런 용도를 위해 AndroidViewModel 클래스를 제공
```

## 코루틴

- ![멀티쓰레딩](https://user-images.githubusercontent.com/31605792/134524165-b3cd5ae5-a76a-4614-8518-53e52b3f48a7.png)
- 쓰레드를 사용하면 간단하게 여러 작업 동시 실행이 가능하나, 쓰레드를 코드에 __직접__ 사용하면 여러 문제 발생할 수 있다.
1. 많은 리소스가 필요 (한정된 시간)
2. 경합 상태 및 예측할 수 없는 동작
    - 여러 스레드로 작업할 때는 경합 상태도 발생할 수 있음
    - 여러 스레드가 동시에 메모리의 동일한 값에 액세스하려고 할 때 발생
    - 경합 상태로 인해 무작위로 보이는 버그를 재현하기 어려울 수 있고 이로 인해 예상치 못한 앱의 비정상 종료를 유발
    
- 출처 : [코드랩](https://developer.android.com/codelabs/basic-android-kotlin-training-introduction-coroutines?continue=https%3A%2F%2Fcodelabs.developers.google.com%2F#2)

```
용어 :

Job - 취소 가능한 작업 단위(예: launch() 함수로 만든 작업 단위)입니다.
CoroutineScope - launch() 및 async()와 같은 새 코루틴을 만드는 데 사용되는 함수는 CoroutineScope를 확장합니다.
Dispatcher - 코루틴이 사용할 스레드를 결정합니다. Main 디스패처는 항상 기본 스레드에서 코루틴을 실행하지만 Default나 IO, Unconfined와 같은 디스패처는 다른 스레드를 사용합니다.
runBlocking() - 새 코루틴을 시작하고 완료될 때까지 현재 스레드를 차단하는 runBlocking()을 사용
async() - Deferred 유형의 값을 반환합니다. Deferred는 미래 값 참조를 보유할 수 있는 취소 가능한 Job
suspend 함수 - 비동기 실행을 위한 중단 지점
```
출처 : [코드랩](https://developer.android.com/codelabs/basic-android-kotlin-training-introduction-coroutines?continue=https%3A%2F%2Fcodelabs.developers.google.com%2F#3)

```
suspend는 모든 로컬 변수를 저장하여 현재 코루틴 실행을 정지합니다.
resume은 정지된 위치부터 정지된 코루틴을 계속 실행합니다.
```
    - Kotlin 코루틴은 디스패처를 사용하여 코루틴 실행에 사용되는 스레드를 결정
    - 코드를 기본 스레드 외부에서 실행하려면 기본 또는 IO 디스패처에서 작업을 실행하도록 Kotlin 코루틴에 지시하면 됩니다. Kotlin에서 모든 코루틴은 기본 스레드에서 실행 중인 경우에도 디스패처에서 실행되어야 합니다. 코루틴이 정지될 수 있으며 디스패처는 코루틴을 계속해야 합니다.

```
Dispatchers.Main - 이 디스패처를 사용하여 기본 Android 스레드에서 코루틴을 실행합니다. 이 디스패처는 UI와 상호 작용하고 빠른 작업을 수행하기 위해서만 사용해야 합니다. 예를 들어 suspend 함수를 호출하고, Android UI 프레임워크 작업을 실행하고, LiveData 객체를 업데이트합니다.
Dispatchers.IO - 이 디스패처는 기본 스레드 외부에서 디스크 또는 네트워크 I/O를 수행하도록 최적화되어 있습니다. 예를 들어 회의실 구성요소를 사용하고 파일을 읽거나 쓰고 네트워크 작업을 실행합니다.
Dispatchers.Default - 이 디스패처는 CPU를 많이 사용하는 작업을 기본 스레드 외부에서 수행하도록 최적화되어 있습니다. 예를 들어 목록을 정렬하고 JSON을 파싱합니다.
```

- MVVM 패턴

특징: 
view가 viewModel이 가진 데이터를 옵저빙하고 있다가 viewModel의 데이터가 변경되면 자동으로 view도 갱신되는 방법
view->viewModel의 의존성을 줄이기 위해 databinding을 사용

출처: https://umbum.dev/915

## Thread & Coroutine
Thread, Coroutine 모두 Concurrency 동시성 (Interleaving) 를 보장하기 위한 기술입니다. 여러개의 작업을 동시에 수행할 때 Thread 는 각 작업에 해당하는 메모리 영역을 할당하는데, 여러 작업을 동시에 수행해야하기 때문에 OS 레벨에서 각 작업들을 얼만큼씩 분배하여 수행해야지 효율적일지 Preempting Scheduling 을 필요로 합니다. A 작업 조금 B 작업 조금을 통해 최종적으로 A 작업과 B 작업 모두를 이뤄내는 것입니다. Coroutine 은 Lightweight Thread 라고 불립니다. 이 또한 작업을 효율적으로 분배하여 조금씩 수행하여 완수하는 Concurrency 를 목표로하지만 각 작업에 대해 Thread 를 할당하는 것이 아니라 작은 Object 만을 할당해주고 이 Object 들을 자유자재로 스위칭함으로써 Switching 비용을 최대한 줄였습니다.

출처 : https://aaronryu.github.io/2019/05/27/coroutine-and-thread/

## 코루틴

기본 function에서 단순히 suspend만 붙임으로써 cortouine을 이용하여 background에서 동작하는 함수를 생성할 수 있습니다.

출처: https://tourspace.tistory.com/272 [투덜이의 리얼 블로그]

## 수명 주기 인식 코루틴 범위

ViewModelScope는 앱의 각 ViewModel에서 정의

```
class MyViewModel: ViewModel() {
        init {
            viewModelScope.launch {
                // Coroutine that will be canceled when the ViewModel is cleared.
            }
        }
    }
```


LifecycleScope는 각 Lifecycle 개체에서 정의됩니다. 이 범위에서 시작된 모든 코루틴은 Lifecycle이 끝날 때 취소

```
class MyFragment: Fragment() {
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewLifecycleOwner.lifecycleScope.launch {
                val params = TextViewCompat.getTextMetricsParams(textView)
                val precomputedText = withContext(Dispatchers.Default) {
                    PrecomputedTextCompat.create(longTextContent, params)
                }
                TextViewCompat.setPrecomputedText(textView, precomputedText)
            }
        }
    }
```


- CoroutineScope, viewModelScope


## withContext()
코루틴을 사용하면 세부적인 제어를 통해 스레드를 전달할 수 있습니다. withContext()를 사용하면 콜백을 도입하지 않고도 코드 줄의 스레드 풀을 제어할 수 있으므로 데이터베이스 읽기, 네트워크 요청 수행과 같은 매우 작은 함수에 이를 적용할 수 있습니다. withContext()를 사용하여 모든 함수가 기본적으로 안전한지, 즉 기본 스레드에서 함수를 호출할 수 있는지 확인하는 것이 좋습니다. 이 경우 호출자는 함수를 실행하는 데 사용할 스레드를 생각할 필요가 없습니다.

# companion object

- 클래스의 인스턴스와 상관없이 호출해야 하지만 class의 내부 정보에 접근할수 있는 함수가 필요할때 companion object를 class 내부에 선언

출처: https://tourspace.tistory.com/109 [투덜이의 리얼 블로그]


## constraint Layout 톹아보기
### chain style
#### spread inside
- ![spread_inside](https://user-images.githubusercontent.com/31605792/134912399-00ff9273-e993-4791-80c3-f4c848af138c.png)
#### spread
- ![spread](https://user-images.githubusercontent.com/31605792/134912403-094063ea-1880-4349-acbe-4cbde3405132.png)
#### packed
- ![packed](https://user-images.githubusercontent.com/31605792/134912395-8b69f693-1970-4e5c-9d41-aafd8bccf1ec.png)
#### weighted
- ![weighted](https://user-images.githubusercontent.com/31605792/134912407-63d70958-bfbb-4e1e-8eca-fb858e3a3b09.png)


## proguard 
### multidex
- 앱 및 앱이 참조하는 라이브러리에서 메서드가 65,536개를 초과하면 앱이 Android 빌드 아키텍처의 제한에 도달했음을 알리는 빌드 오류가 발생
- 단일 DEX(Dalvik Executable) 바이트 코드 파일 내에서 코드가 호출할 수 있는 참조의 총 개수
- 참고) 계측 테스트 
```
fun onCreate(arguments: Bundle) {
  MultiDex.install(targetContext) // AndroidJUnitRunner로 사용권장
  super.onCreate(arguments)
  ...
}
```
### multidex 단점
- 멀티덱스를 사용하면 큰 어려움 없이 큰 규모의 앱을 빌드 가능 
- 빌드 과정에서 앱 내에서 사용하는 클래스를 여러 개의 덱스 파일로 나눔 -> 이를 위해 별도의 분석 작업이 추가 -> 빌드 속도가 느려짐.
- 실제로 앱에서 사용하지 않는 클래스도 모두 포함하므로 앱 용량도 늘어남

출처: https://www.androidhuman.com/2017-02-16-reduce-method-count-with-proguard

### proguard

1. 코드 난독화를 통해 디컴파일시 본인의 코드가 노출되는 것을 방지
2. 불필요한 메서드를 제거하여 멀티덱스를 피할 수 있음

출처 : https://black-jin0427.tistory.com/89
