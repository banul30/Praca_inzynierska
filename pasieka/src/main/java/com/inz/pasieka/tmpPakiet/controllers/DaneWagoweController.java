package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.dto.WagaDTOS.DaneWagoweDTO;
import com.inz.pasieka.tmpPakiet.entities.DaneWagowe;
import com.inz.pasieka.tmpPakiet.services.DaneWagoweService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/tests/daneWagowe")
public class DaneWagoweController {

    private final DaneWagoweService daneWagoweService;

    public DaneWagoweController(DaneWagoweService daneWagoweService) {
        this.daneWagoweService = daneWagoweService;
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:read')) and @userCheckUtils.checkWagaPermissions(#wagaId, authentication.principal)")
    @GetMapping(path="{wagaId}")
    public List<DaneWagoweDTO> getDaneWagoweByWagaId(@PathVariable("wagaId")Long wagaId) {
        List<DaneWagowe> daneWagowe = daneWagoweService.getDaneWagoweByWagaId(wagaId);
        List<DaneWagoweDTO> daneWagoweDTO = new ArrayList<>();
        for (DaneWagowe wagowe : daneWagowe) {
            daneWagoweDTO.add(new DaneWagoweDTO(wagowe.getMaxWeight(), wagowe.getDate(), "2400", "2400"));
        }
        return daneWagoweDTO;
    }



}
