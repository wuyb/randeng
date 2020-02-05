package com.randeng.api.controller.admin;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Hospital;
import com.randeng.api.model.Inventory;
import com.randeng.api.model.Logistic;
import com.randeng.api.service.FundraisingService;
import com.randeng.api.service.HospitalService;
import com.randeng.api.service.LogisticService;
import com.randeng.tools.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller("fundraisingController")
@RequestMapping("admin/fundraising")
public class FundraisingController extends BaseController {

    @Resource(name = "fundraisingServiceImpl")
    private FundraisingService fundraisingService;

    @Resource(name = "hospitalServiceImpl")
    private HospitalService hospitalService;

    @Resource(name = "logisticServiceImpl")
    private LogisticService logisticService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Fundraising request) {
        if (StringUtils.isEmpty(request.getName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The name is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (StringUtils.isEmpty(request.getManufacturer())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The manufacturer is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (StringUtils.isEmpty(request.getOrigin())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The origin is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (StringUtils.isEmpty(request.getBrand())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The brand is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (request.getProductPhotos() == null || request.getProductPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The product photo is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (StringUtils.isEmpty(request.getSpecification())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The specification is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (StringUtils.isEmpty(request.getLocation())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The location is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (request.getCertificatePhotos() == null || request.getCertificatePhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The certificate photos is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (request.getTotalPrice() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The total price is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (request.getAmount() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The amount is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (request.getHospitals() == null || request.getHospitals().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The hospitals is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (request.getStartTime() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The start time is required.", ErrorCode.INVALID_FUNDRAISING));
        }
        if (request.getEndTime() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The end time is required.", ErrorCode.INVALID_FUNDRAISING));
        }

        request.setStatus(Fundraising.FundRaisingStatus.OPEN);
        fundraisingService.save(request);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) Fundraising.FundRaisingStatus status,
                           @RequestParam(required = false) Long hospitalId,
                           @RequestParam(required = false) Long province,
                           @RequestParam(required = false) Long city,
                           @RequestParam(required = false) Long region,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (status != null) {
            pageable.getFilters().add(new Filter("status", Filter.Operator.eq, status));
        }
        if (startTime != null) {
            pageable.getFilters().add(new Filter("endTime", Filter.Operator.ge, DateUtils.startOfDay(startTime)));
        }
        if (endTime != null) {
            pageable.getFilters().add(new Filter("endTime", Filter.Operator.le, DateUtils.endOfDay(endTime)));
        }
        // if hospital is specified, ignore province/city/region restrictions
        if (hospitalId != null) {
            Hospital hospital = hospitalService.find(hospitalId);
            if (hospital == null) {
                return ResponseEntity.notFound().build();
            }
            Page<Fundraising> fundraisingPage = fundraisingService.findPageByHospital(hospital, pageable);
            return ResponseEntity.ok(WebResponse.success(fundraisingPage));
        } else if (province != null || city != null || region != null) {
            String attribute = null;
            Long attrValue = null;
            if (province != null) {
                attribute = "provinceId";
                attrValue = province;
            }
            if (city != null) {
                attribute = "cityId";
                attrValue = city;
            }
            if (region != null) {
                attribute = "regionId";
                attrValue = region;
            }
            List<Hospital> hospitals = hospitalService.findList(null, Arrays.asList(new Filter(attribute, Filter.Operator.eq, attrValue)), null);
            if (hospitals.size() == 0) {
                return ResponseEntity.notFound().build();
            }
            Page<Fundraising> fundraisingPage = fundraisingService.findPageByHospitals(hospitals, pageable);
            return ResponseEntity.ok(WebResponse.success(fundraisingPage));
        } else {
            Page<Fundraising> fundraisingPage = fundraisingService.findPage(pageable);
            return ResponseEntity.ok(WebResponse.success(fundraisingPage));
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(WebResponse.success(fundraisingService.find(id)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fundraising request) {
        Fundraising fundraising = fundraisingService.find(id);
        if (!StringUtils.isEmpty(request.getName())) {
            fundraising.setName(request.getName());
        }
        if (!StringUtils.isEmpty(request.getManufacturer())) {
            fundraising.setManufacturer(request.getManufacturer());
        }
        if (!StringUtils.isEmpty(request.getOrigin())) {
            fundraising.setOrigin(request.getOrigin());
        }
        if (!StringUtils.isEmpty(request.getBrand())) {
            fundraising.setBrand(request.getBrand());
        }
        if (request.getProductPhotos() != null) {
            fundraising.setProductPhotos(request.getProductPhotos());
        }
        if (!StringUtils.isEmpty(request.getSpecification())) {
            fundraising.setSpecification(request.getSpecification());
        }
        if (!StringUtils.isEmpty(request.getLocation())) {
            fundraising.setLocation(request.getLocation());
        }
        if (request.getCertificatePhotos() != null) {
            fundraising.setCertificatePhotos(request.getCertificatePhotos());
        }
        if (request.getTotalPrice() != null) {
            fundraising.setTotalPrice(request.getTotalPrice());
        }
        if (request.getAmount() != null) {
            fundraising.setAmount(request.getAmount());
        }
        if (request.getHospitals() != null) {
            fundraising.setHospitals(request.getHospitals());
        }
        if (request.getStartTime() != null) {
            fundraising.setStartTime(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            fundraising.setEndTime(request.getEndTime());
        }
        if (request.getStatus() != null) {
            fundraising.setStatus(request.getStatus());
        }
        fundraisingService.update(fundraising);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "{id}/inventory", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> setInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        Fundraising fundraising = fundraisingService.find(id);
        if (fundraising == null) {
            return ResponseEntity.notFound().build();
        }
        inventory.setFundraising(fundraising);
        fundraising.setInventory(inventory);
        fundraisingService.update(fundraising);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "{id}/logistic", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> addLogistics(@PathVariable Long id, @RequestBody List<Logistic> logistics) {
        Fundraising fundraising = fundraisingService.find(id);
        if (fundraising == null) {
            return ResponseEntity.notFound().build();
        }
        if (fundraising.getLogistics() == null) {
            fundraising.setLogistics(new ArrayList<>());
        }
        for (Logistic logistic : logistics) {
            if (logistic.getHospital() == null) {
                return ResponseEntity.badRequest().body("Hospital is required.");
            }
            boolean foundHospital = false;
            for (Hospital hospital : fundraising.getHospitals()) {
                if (hospital.getId().equals(logistic.getHospital().getId())) {
                    foundHospital = true;
                    break;
                }
            }
            if (!foundHospital) {
                return ResponseEntity.badRequest().body("The hospital " + logistic.getHospital().getId() + " is not in fundraising " + fundraising.getId());
            }
            logistic.setStatus(Logistic.LogisticStatus.SHIPPED);
            logistic.setFundraising(fundraising);
            fundraising.getLogistics().add(logistic);
        }
        fundraisingService.update(fundraising);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "{fundraisingId}/logistic/{logisticId}", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> updateLogisticToDelivered(@PathVariable Long fundraisingId, @PathVariable Long logisticId, @RequestBody Logistic logistic) {
        Fundraising fundraising = fundraisingService.find(fundraisingId);
        if (fundraising == null) {
            return ResponseEntity.notFound().build();
        }
        Logistic logisticToUpdate = null;
        for (Logistic l : fundraising.getLogistics()) {
            if (l.getId().equals(logisticId)) {
                logisticToUpdate = l;
                break;
            }
        }
        if (logisticToUpdate == null) {
            return ResponseEntity.badRequest().body("The logistic " + logisticId + " is not in fundraising " + fundraisingId);
        }
        logisticToUpdate.setActualDeliveryDate(logistic.getActualDeliveryDate());
        logisticToUpdate.setDeliveryPhotos(logistic.getDeliveryPhotos());
        logisticToUpdate.setStatus(Logistic.LogisticStatus.DELIVERED);
        logisticService.update(logisticToUpdate);
        return ResponseEntity.ok(WebResponse.success(true));
    }
}
