package com.tangerinagames.tangauth.model;


import com.avaje.ebean.EbeanServer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ModelTest {

    private class FakeModel extends Model {}

    @Mock
    private EbeanServer database = mock(EbeanServer.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        FakeModel.setDatabase(database);
    }

    @Test
    public void shouldBeAbstract() throws NoSuchFieldException {
        assertTrue(Modifier.isAbstract(Model.class.getModifiers()));
    }

    @Test
    public void shouldHaveAIdField() throws NoSuchFieldException {
        Field id = Model.class.getDeclaredField("id");
        assertTrue(id.isAnnotationPresent(Id.class));
    }

    @Test
    public void shouldCreateAModel() {
        FakeModel model = new FakeModel();
        when(database.createEntityBean(FakeModel.class)).thenReturn(model);

        assertEquals(model, FakeModel.create(FakeModel.class));
    }

    @Test
    public void shouldFindAModel() {
        int key = 1;
        FakeModel model = new FakeModel();
        when(database.find(FakeModel.class, key)).thenReturn(model);

        assertEquals(model, FakeModel.find(FakeModel.class, key));
    }

    @Test
    public void shouldSaveAModel() {
        FakeModel model = new FakeModel();
        model.save();

        verify(database).save(model);
    }

}
