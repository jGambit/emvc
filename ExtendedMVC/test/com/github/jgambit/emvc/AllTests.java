package com.github.jgambit.emvc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.jgambit.emvc.controller.ModulControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	ModulControllerTest.class, 
	})
public class AllTests {
	
}
