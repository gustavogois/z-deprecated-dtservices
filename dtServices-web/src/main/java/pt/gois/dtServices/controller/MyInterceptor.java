package pt.gois.dtServices.controller;

import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class MyInterceptor {

	@AroundConstruct
	private void onInit(InvocationContext ic) {
		try {
			System.out.println("Algo inicializando...");
			ic.proceed();
		} catch (Exception ex) {

		} finally {
			System.out.println("Fim da inicialização");
		}
	}
	
	@AroundInvoke
	private Object execute(InvocationContext ic) {
		Object obj = null;
		try {
			System.out.println("Execução do Interceptor");
			obj = ic.proceed();
		} catch (Exception ex) {

		} finally {
			System.out.println("Execução do Interceptor");
		}
		return obj;
	}

}
