/* 
 * Copyright 2011-2013 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.commonline.weather.persist;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import uk.commonline.data.access.Dao;
import uk.commonline.data.model.EI;

/**
 */
public abstract class AbstractDataAccessImplTests<T extends EI<T>> {
	
	protected abstract EntityManager getRepository();
	
	protected abstract Dao<T> getDao();
	
	@Test
	public void testFindAll() {
		List<T> list = getDao().getAll();
		assertNotNull(list);
	}
	
	@Test
	public void testFindOne() {
		T entity = getDao().get(1L);
		assertNotNull(entity);
	}
	
	@Test
	public void testDelete() {
		getDao().deleteById(1L);
		verify(getRepository(), times(1)).remove(1L);
	}
}
