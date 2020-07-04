package br.com.alura.forum.controller;

import br.com.alura.forum.dto.output.DashboardDto;
import br.com.alura.forum.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/dashboard")
@RestController
public class DashboardController {

    private DashboardService dashboardService;

    @GetMapping
    public List<DashboardDto> getDashboard() {
        return dashboardService.getDashboardStatistics();
    }

}
