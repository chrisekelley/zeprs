/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 8, 2004
 * Time: 11:06:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class RuleUtils {
    private static Log log = LogFactory.getFactory().getInstance("RuleUtils");

    public static List getRules(List ruleDefs) {
        List rules = new ArrayList();
        RuleDefinition ruleDef;
        Rule ruleInstance;
        for (int i = 0; i < ruleDefs.size(); i++) {
            ruleDef = (RuleDefinition) ruleDefs.get(i);
            try {
                ruleInstance = ruleDef.getInstance();
                rules.add(ruleInstance);
            } catch (Exception e) {
                log.error("error instantiating Rule, rule skipped. Id=" + ruleDef.getId(), e);
            }
        }
        return rules;

    }
}
