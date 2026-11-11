package a12EnumeracoesEComposicao.ex1Resolvido.entity;

import a12EnumeracoesEComposicao.ex1Resolvido.entity.enums.WorkerLevel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Worker {
    private String      name;
    private WorkerLevel workerLevel;
    private Double      baseSalary;

    private Department         department;
    private List<HourContract> contracts = new ArrayList<>();

    public void setDepartment(
            Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public Worker(String name, WorkerLevel workerLevel, Double baseSalary,
                  Department department) {
        this.name = name;
        this.workerLevel = workerLevel;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public Worker() {
    }

    public Worker(String name, WorkerLevel workerLevel, Double baseSalary) {
        this.name = name;
        this.workerLevel = workerLevel;
        this.baseSalary = baseSalary;
        this.contracts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getWorkerLevel() {
        return workerLevel;
    }

    public void setWorkerLevel(
            WorkerLevel workerLevel) {
        this.workerLevel = workerLevel;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void addContract(HourContract contract) {
        this.contracts.add(contract);
    }

    public void removeContract(HourContract contract) {
        this.contracts.remove(contract);
    }

    public Double income(Integer year, Integer month) {
        double sum = baseSalary;
        Calendar cal = Calendar.getInstance();
        for (HourContract hc : contracts) {
            cal.setTime(hc.getDate());
            int hcYear = cal.get(Calendar.YEAR);
            int hcMonth = 1 + cal.get(Calendar.MONTH);
            if (year == hcYear && month == hcMonth) {
                sum += hc.totalValue();
            }
        }
        return sum;
    }
}
