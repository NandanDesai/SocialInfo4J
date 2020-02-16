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
package io.github.nandandesai.instagram4j.utils;

import java.util.UUID;

/**
 * Generic utils
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramGenericUtil {
    
    /**
     * Generate UUID
     * @param dash If needs to keep dash
     * @return UUID
     */
    public static String generateUuid(boolean dash) {
        String uuid = UUID.randomUUID().toString();
        
        if (dash) {
            return uuid;
        }
        
        return uuid.replaceAll("-", "");
    }
}
