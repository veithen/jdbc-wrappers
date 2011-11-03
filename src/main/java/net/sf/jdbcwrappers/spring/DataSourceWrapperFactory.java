/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.sf.jdbcwrappers.spring;

import javax.sql.DataSource;

import net.sf.jdbcwrappers.WrapperFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class DataSourceWrapperFactory implements FactoryBean, InitializingBean {
    private WrapperFactory wrapperFactory;
    private DataSource dataSource;
    private DataSource wrappedDataSource;
    
    public void setWrapperFactory(WrapperFactory wrapperFactory) {
        this.wrapperFactory = wrapperFactory;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void afterPropertiesSet() throws Exception {
        if (wrapperFactory == null) {
            throw new Exception("wrapperFactory not set"); 
        }
        if (dataSource == null) {
            throw new Exception("dataSource not set"); 
        }
        wrappedDataSource = wrapperFactory.wrapDataSource(dataSource);
    }

    public boolean isSingleton() {
        return true;
    }
    
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    public Object getObject() throws Exception {
        return wrappedDataSource;
    }
}
