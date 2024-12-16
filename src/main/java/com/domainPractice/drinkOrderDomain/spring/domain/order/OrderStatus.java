package com.domainPractice.drinkOrderDomain.spring.domain.order;

public enum OrderStatus {
    PENDING {
        @Override
        public boolean isTransitionAllowed(OrderStatus newStatus) {
            return newStatus == IN_PROGRESS;
        }
    },
    IN_PROGRESS {
        @Override
        public boolean isTransitionAllowed(OrderStatus newStatus) {
            return newStatus == READY;
        }
    },
    READY {
        @Override
        public boolean isTransitionAllowed(OrderStatus newStatus) {
            return newStatus == COMPLETED;
        }
    },
    COMPLETED {
        @Override
        public boolean isTransitionAllowed(OrderStatus newStatus) {
            return false; // 완료 상태에서는 더 이상 상태 전환 불가능
        }
    };

    /**
     * 상태 전환 가능 여부 확인
     */
    public abstract boolean isTransitionAllowed(OrderStatus newStatus);
}