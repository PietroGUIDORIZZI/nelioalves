package a16Interfaces.services;

import a16Interfaces.entity.Contract;

import java.time.LocalDate;

public
class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract,
                                int months) {
        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract
                    .getDate()
                    .plusMonths(i);
        }
    }
}
