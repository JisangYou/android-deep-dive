# 아키텍쳐 구성

- Data Binding - Declaratively bind observable data to UI elements.
- Lifecycles - Create a UI that automatically responds to lifecycle events.
- LiveData - Build data objects that notify views when the underlying database changes.
- Navigation - Handle everything needed for in-app navigation.
- Room - Access your app's SQLite database with in-app objects and compile-time checks.
- ViewModel - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
- WorkManager - Manage your Android background jobs
- Coroutine

## 프로젝트 
```

Android에서 코루틴은 다음 두 가지 기본 문제를 해결하는 데 도움이 됩니다.
기본 스레드를 차단하여 앱이 정지될 수 있는 장기 실행 작업을 관리합니다.
기본 안전, 즉 기본 스레드에서 네트워크 또는 디스크 작업을 안전하게 호출하는 기능을 제공합니다.

```

```
RecyclerView 어댑터에서 두 리스트의 차이를 계산하는 DiffUtil이 있습니다. 이 클래스는 약간의 보일러플레이트 코드와 두 리스트의 비교 처리를 (권고사항으로) 백그라운드 스레드에서 실행 후 결과를 메인 스레드에서 처리하는 코드가 필요했습니다. ListAdapter는 내부적으로 AsyncListDiffer을 사용해 개발자가 직접 DiffUtil을 사용할 때 필요했던 처리를 대신 다룹니다. 이를 통해 더 적은 코드로 두 리스트의 차이를 계산해서 변경이 발생한 부분만 업데이트
ListAdapter는 내부적으로 리스트를 읽기만 가능한 불변 객체로 다룹니다. 따라서 전달된 리스트에서 항목을 직접 변경하는 것을 허용하지 않고, 만일 변경한다고 하더라도 업데이트는 반영되지 않습니다. 리스트에서 항목이 수정, 추가, 삭제, 이동이 발생하는 경우, 반드시 변경이 반영된 새로운 리스트를 ListAdapter로 전달해야 합니다. 백그라운드 스레드에서 리스트 변경 사항이 계산되면 내부적으로 notifyItem*() 함수가 호출되고, 사용자는 업데이트 된 RecyclerView를 볼 수 있습니다. 이는 기존의 RecyclerView 어댑터와의 차이점이자 ListAdapter의 특징
```

[출처](https://medium.com/@jungil.han/recyclerview-%EA%B0%9C%EB%B0%9C%EC%97%90-%EB%82%A0%EA%B0%9C-%EB%8B%AC%EA%B8%B0-539e08291160)