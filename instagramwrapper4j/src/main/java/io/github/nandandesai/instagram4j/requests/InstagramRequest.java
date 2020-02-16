/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.nandandesai.instagram4j.requests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nandandesai.instagram4j.Instagram4J;
import io.github.nandandesai.instagram4j.requests.payload.StatusResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class InstagramRequest<T> {

    protected Instagram4J api;
    
    /**
     * @return the url
     */
    public abstract String getUrl();
    
    /**
     * @return the method
     */
    public abstract String getMethod();
    
    /**
     * @return the payload
     */
    public String getPayload() throws IOException, InstantiationException, IllegalAccessException {
        return null;
    }
    
    /**
     * @return the result
     * @throws IOException
     */
    public abstract T execute() throws IOException, IllegalAccessException, InstantiationException;
    
    /**
     * Process response
     * @param resultCode Status Code
     * @param content Content
     */
    public abstract T parseResult(int resultCode, String content) throws IOException, InstantiationException, IllegalAccessException;
    
    /**
     * @return if request must be logged in
     */
    public boolean requiresLogin() {
        return true;
    }

    /**
     * Parses Json into type, considering the status code
     * @param statusCode HTTP Status Code
     * @param str Entity content
     * @param clazz Class
     * @return Result
     */
    public <U> U parseJson(int statusCode, String str, Class<U> clazz) throws IOException, IllegalAccessException, InstantiationException {
        
        if (clazz.isAssignableFrom(StatusResult.class)) {
            
            //TODO: implement a better way to handle exceptions
            if (statusCode == 404) {
                StatusResult result = (StatusResult) clazz.newInstance();
                result.setStatus("error");
                result.setMessage("SC_NOT_FOUND");
                return (U) result;
            } else if (statusCode == 403) {
                StatusResult result = (StatusResult) clazz.newInstance();
                result.setStatus("error");
                result.setMessage("SC_FORBIDDEN");
                return (U) result;
            }
}
        
        return parseJson(str, clazz);
    }
    
    /**
     * Parses Json into type
     * @param str Entity content
     * @param clazz Class
     * @return Result
     */
    public <U> U parseJson(String str, Class<U> clazz) throws IOException {
        
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        return objectMapper.readValue(str, clazz);
    }
    
    /**
     * Parses Json into type
     * @param is Entity stream
     * @param clazz Class
     * @return Result
     */
    public T parseJson(InputStream is, Class<T> clazz) throws IOException {
        return this.parseJson(readContentFromStream(is), clazz);
    }

    /**
     * @return payload should be signed
     */
    public boolean isSigned() {
        return true;
    }

    public Instagram4J getApi() {
        return api;
    }

    public void setApi(Instagram4J api) {
        this.api = api;
    }
    private String readContentFromStream(InputStream is) {
        String ret = "";
        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            StringBuffer out = new StringBuffer();

            while ((line = in.readLine()) != null) {
                out.append(line).append("\r\n");
            }
            ret = out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
