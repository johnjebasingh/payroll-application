package com.payroll.repository;

import com.payroll.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    // Check if payroll already exists (important for bulk)
    boolean existsByEmployee_IdAndMonthYear(Long empId, String monthYear);

    // Get payroll for single employee & month
    Optional<Payroll>  findByEmployee_IdAndMonthYear(Long empId, String monthYear);

    // List payrolls by month (reports / UI)
    List<Payroll> findByMonthYear(String monthYear);
    @Query("""
SELECT p.monthYear, SUM(p.netSalary)
FROM Payroll p
GROUP BY p.monthYear
ORDER BY p.monthYear
""")
    List<Object[]> getMonthlyPayrollExpenses();

}
