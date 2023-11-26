package pl.pwr.ite.utils.service;

import java.io.IOException;

public interface WebClient {

    <R> R get(String path, Class<R> responseType) throws IOException, InterruptedException;
}
