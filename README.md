# 주문 관리 시스템

이 시스템은 고객이 앱을 통해 음료를 주문하고, 직원이 이를 처리하며, 결제 및 주문 상태를 관리하는 데 필요한 기능을 제공합니다.

---

## 주요 도메인 정의

### 1. 회원(Customer)
- **설명**: 고객은 앱을 통해 주문을 생성하고 자신의 주문 상태를 조회할 수 있습니다.
- **주요 속성**:
  - 이름(name)
  - 전화번호(phoneNumber)
  - 고유 ID(customerId)

### 2. 주문(Order)
- **설명**: 주문은 고객이 요청한 세부 정보를 포함하며, 다음 상태를 가집니다:
  - `Pending`: 주문 생성 후 결제가 완료되지 않은 상태
  - `InProgress`: 결제가 완료되고 음료 준비가 진행 중인 상태
  - `Ready`: 음료 준비가 완료된 상태
  - `Completed`: 고객이 음료를 픽업한 상태
- **주요 속성**:
  - 주문 ID(orderId)
  - 주문 상태(orderStatus)
  - 주문 시간(orderTime)
  - 총 금액(totalAmount)

### 3. 음료(Drink)
- **설명**: 음료는 주문에 포함되며, 개별 음료에 대한 세부 정보를 제공합니다.
- **주요 속성**:
  - 음료 ID(drinkId)
  - 이름(drinkName)
  - 크기(size): `Small`, `Medium`, `Large`
  - 추가 옵션(add-ons)
  - 가격(price)

### 4. 직원(Staff)
- **설명**: 직원은 주문 상태를 변경할 수 있으며, 역할에 따라 권한이 다릅니다.
- **주요 속성**:
  - 이름(name)
  - 직원 ID(staffId)
  - 역할(role): `Barista`, `Cashier`
- **역할별 상태 변경 권한**:
  - `Barista`: `InProgress → Ready`
  - `Cashier`: `Pending → InProgress`

### 5. 결제(Payment)
- **설명**: 결제는 주문과 연결되어 있으며, 결제가 완료되어야 주문이 진행됩니다.
- **주요 속성**:
  - 결제 ID(paymentId)
  - 결제 방법(paymentMethod): `Card`, `Cash`, `App`
  - 결제 상태(paymentStatus): `Paid`, `Unpaid`

---

## 기능 요구사항

### 회원(Customer)
1. 고객은 앱을 통해 주문을 생성할 수 있습니다.
2. 고객은 자신의 주문 상태를 조회할 수 있습니다.

### 주문(Order)
1. 주문은 반드시 순차적으로 상태가 변경되어야 합니다:
   - `Pending → InProgress → Ready → Completed`

### 음료(Drink)
1. 음료의 재고가 부족할 경우 주문을 생성할 수 없습니다.

### 직원(Staff)
1. 직원은 자신의 역할에 따라 주문 상태를 변경할 수 있습니다:
   - `Barista`: `InProgress → Ready`
   - `Cashier`: `Pending → InProgress`

### 결제(Payment)
1. 결제가 완료되지 않으면 주문 상태는 `Pending`에서 변경되지 않습니다.

---

## 제약사항
1. **주문 생성**: 결제가 완료되지 않으면 주문 상태는 `Pending`에서 변경되지 않습니다.
2. **재고 관리**: 특정 음료의 재고가 부족한 경우 주문이 생성되지 않아야 합니다.
3. **상태 변경 순서**: 주문 상태는 반드시 순차적으로 변경되어야 합니다.
4. **직원의 권한**: 직원의 역할에 따라 상태 변경 권한이 제한됩니다.
