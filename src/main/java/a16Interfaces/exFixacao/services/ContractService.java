package a16Interfaces.exFixacao.services;

import a16Interfaces.exFixacao.entities.Contract;
import a16Interfaces.exFixacao.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months) {

        double basicQuota = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double interest = onlinePaymentService.interest(basicQuota, i);

            double fee = onlinePaymentService.paymentFee(basicQuota + interest);

            double fullQuota = basicQuota + interest + fee;

            Installment installment = new Installment(dueDate, fullQuota);

            contract.getInstalmentList().add(installment);

        }
    }
}
