package br.com.alura.forum.controller;


import br.com.alura.forum.dto.output.DashboardDto;
import br.com.alura.forum.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class DashboardController {

    private DashboardService dashboardService;

    @GetMapping(value="/api/dashboard")
    public List<DashboardDto> getDashboard() {
        return dashboardService.getDashboardStatistics();
    }

}
