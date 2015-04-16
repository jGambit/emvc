package com.github.jgambit.emvc.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.nio.channels.IllegalSelectorException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.github.jgambit.emvc.Foo;
import com.github.jgambit.emvc.exception.ToBeHandledByApplicationException;
import com.github.jgambit.emvc.process.iface.GuiModulProcessIF;


public class ModulControllerTest {

	private class TestUpdatingModulController extends
			TestModulController {
		public TestUpdatingModulController(GuiModulProcessIF modulProcess) {
			super(modulProcess);
		}

		@Override
		protected void setUpdatingForm() {
			setUpdatingMock.bar();
		}

		@Override
		protected void unSetUpdatingForm() {
			unSetUpdatingMock.bar();
		}
	}

	private class TestModulController extends
			ModulController<GuiModulProcessIF, Foo> {

		public TestModulController(GuiModulProcessIF modulProcess) {
			super(modulProcess);
		}

		@Override
		protected void clearFormImpl() throws ToBeHandledByApplicationException {
		}

		@Override
		protected void fillFormImpl(Foo config)
				throws ToBeHandledByApplicationException {
		}

		@Override
		protected void permitFormImpl(boolean permit) {
		}
	}
	
	private class ExceptionFoo {

		void bar() throws ToBeHandledByApplicationException {
			throw new ToBeHandledByApplicationException("Bar");
		}
		
	}
	
	private class PermitFoo {
		void bar(boolean foobar) {};
	}

	ModulController<GuiModulProcessIF, Foo> toTest;
	@Mock private Foo setUpdatingMock;
	@Mock private Foo unSetUpdatingMock;
	@Mock private Foo implMock;
	@Mock private GuiModulProcessIF modulProcess;
	@Mock private Foo currentObject;
	@Mock private Foo shouldClear;
	@Mock private PermitFoo permitFoo;
	private ExceptionFoo exceptionFoo;
	
	@Before
	public void before() {
		initMocks(this);
		toTest = new TestModulController(modulProcess);
		exceptionFoo = new ExceptionFoo();
	}
	
	@Test
	public void testSetUpdatingForm() throws Exception {
		assertFalse(toTest.isUpdatingForm());
		
		toTest.setUpdatingForm();
		
		assertTrue(toTest.isUpdatingForm());
	}

	@Test
	public void testUnSetUpdatingForm() throws Exception {
		toTest.setUpdatingForm();
		toTest.unSetUpdatingForm();
		assertFalse(toTest.isUpdatingForm());
	}

	@Test
	public void testClearForm() throws Exception {
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void clearFormImpl() {
				implMock.bar();
			}
			@Override
			public void permitForm(boolean permit) {
				permitFoo.bar(permit);
			}
		};
		toTest.setCurrentObject(currentObject);
		
		toTest.clearForm();
		
		InOrder order = inOrder(setUpdatingMock, implMock, permitFoo, unSetUpdatingMock);
		order.verify(setUpdatingMock).bar();
		order.verify(implMock).bar();
		order.verify(permitFoo).bar(false);
		order.verify(unSetUpdatingMock).bar();
		assertTrue(toTest.isCleared());
		assertNull(toTest.getCurrentObject());
	}
	
	@Test
	public void testClearFormWithException() throws Exception {
		doThrow(new IllegalSelectorException()).when(modulProcess).startExceptionDialog(any(ToBeHandledByApplicationException.class));
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void clearFormImpl() throws ToBeHandledByApplicationException {
				exceptionFoo.bar();
			}
		};
		toTest.setCurrentObject(currentObject);
		
		try {
			toTest.clearForm();
		} catch (IllegalSelectorException e) {}
		
		verify(setUpdatingMock).bar();
		verify(unSetUpdatingMock).bar();
		assertFalse(toTest.isCleared());
		assertNotNull(toTest.getCurrentObject());
	}

	@Test
	public void testFillFormShouldClearAndPermit() throws Exception {
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void fillFormImpl(Foo config)
					throws ToBeHandledByApplicationException {
				implMock.bar();
			}
			@Override
			public void clearForm() {
				shouldClear.bar();
			}
			@Override
			public void permitForm(boolean permit) {
				permitFoo.bar(permit);
			}
		};
		toTest.setCleared(false);
		
		toTest.fillForm(currentObject);
		
		InOrder order = inOrder(setUpdatingMock, shouldClear, implMock, permitFoo, unSetUpdatingMock);
		order.verify(setUpdatingMock).bar();
		order.verify(shouldClear).bar();
		order.verify(implMock).bar();
		order.verify(permitFoo).bar(true);
		order.verify(unSetUpdatingMock).bar();
	}
	
	@Test
	public void testFillFormShouldNotClear() throws Exception {
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			public void clearForm() {
				implMock.bar();
			}
		};
		toTest.setCleared(true);
		
		toTest.fillForm(currentObject);
		
		verify(implMock, never()).bar();
	}
	
	@Test
	public void testFillFormWithException() throws Exception {
		doThrow(new IllegalStateException()).when(modulProcess).startExceptionDialog(any(ToBeHandledByApplicationException.class));
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void fillFormImpl(Foo config)
					throws ToBeHandledByApplicationException {
				exceptionFoo.bar();
			}
			@Override
			public void clearForm() {
			}
		};
		
		try {
			toTest.fillForm(currentObject);
		} catch (IllegalStateException e) {}
		
		verify(setUpdatingMock).bar();
		verify(unSetUpdatingMock).bar();
	}
	
}
