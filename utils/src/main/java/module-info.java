module pl.pwr.ite.utils {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires java.net.http;

    exports pl.pwr.ite.utils.service;
    exports pl.pwr.ite.utils.service.impl;
    exports pl.pwr.ite.utils.model;
}