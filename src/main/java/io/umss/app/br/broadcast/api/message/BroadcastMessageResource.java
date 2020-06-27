package io.umss.app.br.broadcast.api.message;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.dto.message.BroadcastMessageDTOV;
import io.umss.app.br.broadcast.service.message.BroadcastMessageService;
import io.umss.app.br.broadcast.util.AElog;
import io.umss.app.br.broadcast.util.AEutil;
import io.umss.app.br.broadcast.util.exception.response.custom.CustomRuntimeException;

/**
 * BroadcastMessageResource
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/broadcastmessages")
public class BroadcastMessageResource {

    private final Logger logger = LoggerFactory.getLogger(BroadcastMessageResource.class);

    @Autowired
    private AEutil util;

    @Autowired
    private BroadcastMessageService service;

    @GetMapping
    public ResponseEntity<Object> findAllObjects(@Nullable Integer status, @Nullable Long categoryId,
            @Nullable Long messageId, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page, HttpServletRequest request) {

        Integer totalRecords;
        List<BroadcastMessage> objectList;
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        objectList = service.getAllObjects(Optional.ofNullable(status), Optional.ofNullable(categoryId),
                Optional.ofNullable(messageId), pageSize, page);
        totalRecords = service.getCountAllObjects(Optional.ofNullable(status), Optional.ofNullable(categoryId),
                Optional.ofNullable(messageId));

        responseHeaders.set("X-Total-Count", totalRecords.toString());
        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(objectList, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id, HttpServletRequest request) {

        BroadcastMessage object;
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (null == id) {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Wrong request", "There is a to the param.");
        } else {
            object = service.getObjectById(Optional.ofNullable(id));
            if (null == object) {
                throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "Not found",
                        "There isn't records to the param");
            }
        }

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveObject(@Valid @RequestBody BroadcastMessageDTOV objectDTOV,
            HttpServletRequest request) {

        BroadcastMessage object = new BroadcastMessage();
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (null != objectDTOV) {
            objectDTOV.copyCoreObject(object);
            object = service.save(object);
        } else {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Wrong Request",
                    "The object want to save is null.");
        }

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateObject(@Valid @RequestBody BroadcastMessageDTOV objectDTOV,
            @PathVariable("id") Long id, HttpServletRequest request) {

        BroadcastMessage object = new BroadcastMessage();
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (null != objectDTOV && null != id) {
            object = service.getObjectById(Optional.ofNullable(id));
            if (null != object && null != object.getUid()) {
                objectDTOV.copyCoreObject(object);
                object = service.update(object);
            } else {
                throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "Fail request",
                        "There isn't record you want update.");
            }
        } else {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Wrong request",
                    "There are error belongs params.");
        }

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteObject(@Valid @RequestBody BroadcastMessageDTOV objectDTOV,
            @PathVariable("id") Long id, HttpServletRequest request) {

        BroadcastMessage object = new BroadcastMessage();
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request);

        if (null != objectDTOV && null != id) {
            object = service.getObjectById(Optional.ofNullable(id));
            if (null != object && null != object.getUid()) {
                objectDTOV.copyCoreObject(object);
                service.delete(object);
            } else {
                throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "Operacion fallida",
                        "No existe el registro que desea eliminar.");
            }
        } else {
            throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "Solicitud incorrecta",
                    "Existe un error en el/los parámetro(s).");
        }

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(responseHeaders, HttpStatus.OK);
    }

    private synchronized void requestLog(HttpServletRequest request) {
        AElog.info1(logger,
                util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:" + request.getMethod()
                        + ", URI:" + request.getRequestURI() + ", query:" + request.getQueryString() + "}");
    }
}