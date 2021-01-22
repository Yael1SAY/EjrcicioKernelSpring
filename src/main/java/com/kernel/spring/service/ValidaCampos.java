package com.kernel.spring.service;

import com.kernel.spring.controller.DireccionController;
import com.kernel.spring.model.Direccion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidaCampos {

    private static final Logger LOG = Logger.getLogger(DireccionController.class.getName());
    //Expreciones regulares
    private final String CORREO = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private final String CURP = "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
                                "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                                "[HM]{1}" +
                                "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
                                "[B-DF-HJ-NP-TV-Z]{3}" +
                                "[0-9A-Z]{1}[0-9]{1}$";
    private final String ALFA = "^[a-zA-Z ´]+$";
    private final String ALFANUMERICO = "^[a-zA-Z0-9 ´]+$";
    private final String NUMERO = "^[0-9]+$";
    private final String USERNAME = "^[a-z0-9_-]{1,16}$";
    private final String NUMEROCODIGO = "^[0-9]{1,8}+$";

    public List<String> validaCorreo(Direccion direccion) {
        List<String> error = new ArrayList<>();

        String correo = direccion.getUsuario().getEmail();
        if (!valida(correo, CORREO)) {
            LOG.info("Service El correo ingresado no es valido");
            error.add("El correo ingresado no es valido");
        }

        String curp = direccion.getUsuario().getCurp();
        if (!valida(curp, CURP)) {
            LOG.info("Service La CURP ingresada no es valida");
            error.add("La CURP ingresada no es valida");
        }

        String apellido = direccion.getUsuario().getApellido();
        LOG.info("Apellido:" + apellido);
        if (!valida(apellido, ALFA)) {
            LOG.info("Service El apellido solo permite letras");
            error.add("El campo apellido no es valido solo permite letras");
        }

        String nombre = direccion.getUsuario().getNombre();
        if (!valida(nombre, ALFA)) {
            LOG.info("Service El nombre solo permite letras");
            error.add("El campo nombre no es valido solo permite letras");
        }

        String edad = String.valueOf(direccion.getUsuario().getEdad());
        if (!valida(edad, NUMERO)) {
            LOG.info("Service El apellido solo permite letras");
            error.add("El campo apellido no es valido solo permite letras");
        }

        String numeroExterior = direccion.getNoExterior();
        if (!valida(numeroExterior, NUMEROCODIGO)) {
            LOG.info("Service El numero exterior no permite carateres y maximo 8 digitos");
            error.add("El numero exterior co permite carateres y maximo 8 digitos");
        }

        String codigoPostal = direccion.getCodPostal();
        if (!valida(codigoPostal, NUMEROCODIGO)) {
            LOG.info("Service El codigo postal no permite carateres y maximo 8 digitos");
            error.add("El codigo postal no permite carateres y maximo 8 digitos");
        }

        String username = direccion.getUsuario().getUsername();
        if (!valida(username, USERNAME)) {
            LOG.info("Service El campo nombre de usuario no coincide con el formato");
            error.add("Service El campo nombre de usuario no coincide con el formato");
        }

        String calle = direccion.getCalle();
        if (!valida(calle, ALFA)) {
            LOG.info("Service El campo calle solo permite letras");
            error.add("Service El campo calle solo permite letras");
        }

        String municipio = direccion.getMunicipio();
        if (!valida(municipio, ALFA)) {
            LOG.info("Service El campo municipio solo permite letras");
            error.add("El campo municipio solo permite letras");
        }

        String referencia = direccion.getReferencia();
        if (!valida(referencia, ALFANUMERICO)) {
            LOG.info("Service El campo referencia no permite caracteres especiales");
            error.add("El campo referencia no permite caracteres especiales");
        }

        return error;
    }

    //valido las coincidencias
    private static boolean valida(String campo, String exprecion) {
        Pattern pattern = Pattern.compile(exprecion);
        Matcher matcher = pattern.matcher(campo);
        if (matcher.find() != true) {
            return false;
        }
        return true;
    }
}


