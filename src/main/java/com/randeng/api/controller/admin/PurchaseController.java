package com.randeng.api.controller.admin;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Purchase;
import com.randeng.api.service.FundraisingService;
import com.randeng.api.service.PurchaseService;
import com.randeng.tools.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller("purchaseController")
@RequestMapping("admin/purchase")
public class PurchaseController extends BaseController {

    @Resource(name = "fundraisingServiceImpl")
    private FundraisingService fundraisingService;

    @Resource(name = "purchaseServiceImpl")
    private PurchaseService purchaseService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Purchase request) {
        if (request.getFundraisings() == null || request.getFundraisings().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The fundraisings is required.", ErrorCode.INVALID_PURCHASE));
        }
        if (StringUtils.isEmpty(request.getPurchaser())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The purchaser is required.", ErrorCode.INVALID_PURCHASE));
        }
        if (request.getPurchaseDate() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The purchase time is required.", ErrorCode.INVALID_PURCHASE));
        }
        if (request.getAmount() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The amount is required.", ErrorCode.INVALID_PURCHASE));
        }
        if (request.getInvoicePhotos() == null || request.getInvoicePhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The invoice photo is required.", ErrorCode.INVALID_PURCHASE));
        }
        if (request.getTransferPhotos() == null || request.getTransferPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The transfer photo is required.", ErrorCode.INVALID_PURCHASE));
        }
        if (request.getFieldPhotos() == null || request.getFieldPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The field photo is required.", ErrorCode.INVALID_PURCHASE));
        }

        request.setStatus(Purchase.PurchaseStatus.PURCHASED);
        purchaseService.save(request);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) Purchase.PurchaseStatus status,
                           @RequestParam(required = false) Long fundraisingId,
                           @RequestParam(required = false) Long id,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (id != null) {
            pageable.getFilters().add(new Filter("id", Filter.Operator.eq, id));
        }
        if (status != null) {
            pageable.getFilters().add(new Filter("status", Filter.Operator.eq, status));
        }
        if (startTime != null) {
            pageable.getFilters().add(new Filter("purchaseDate", Filter.Operator.ge, DateUtils.startOfDay(startTime)));
        }
        if (endTime != null) {
            pageable.getFilters().add(new Filter("purchaseDate", Filter.Operator.le, DateUtils.endOfDay(endTime)));
        }
        if (fundraisingId != null) {
            Fundraising fundraising = fundraisingService.find(fundraisingId);
            if (fundraising == null) {
                return ResponseEntity.notFound().build();
            }
            Page<Purchase> purchasePage = purchaseService.findPageByFundraising(fundraising, pageable);
            return ResponseEntity.ok(WebResponse.success(purchasePage));
        } else {
            Page<Purchase> purchasePage = purchaseService.findPage(pageable);
            return ResponseEntity.ok(WebResponse.success(purchasePage));
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(WebResponse.success(purchaseService.find(id)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Purchase request) {
        Purchase purchase = purchaseService.find(id);
        if (request.getFundraisings() != null && request.getFundraisings().size() > 0) {
            purchase.setFundraisings(request.getFundraisings());
        }
        if (!StringUtils.isEmpty(request.getPurchaser())) {
            purchase.setPurchaser(request.getPurchaser());
        }
        if (request.getPurchaseDate() != null) {
            purchase.setPurchaseDate(request.getPurchaseDate());
        }
        if (request.getAmount() != null) {
            purchase.setAmount(request.getAmount());
        }
        if (request.getInvoicePhotos() != null && request.getInvoicePhotos().size() > 0) {
            purchase.setInvoicePhotos(request.getInvoicePhotos());
        }
        if (request.getTransferPhotos() != null && request.getTransferPhotos().size() > 0) {
            purchase.setTransferPhotos(request.getTransferPhotos());
        }
        if (request.getFieldPhotos() != null && request.getFieldPhotos().size() > 0) {
            purchase.setFieldPhotos(request.getFieldPhotos());
        }
        if (request.getStatus() != null) {
            purchase.setStatus(request.getStatus());
        }
        purchaseService.update(purchase);
        return ResponseEntity.ok(WebResponse.success(true));
    }
}
