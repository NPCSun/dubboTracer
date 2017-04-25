/*package com.sun.javassit;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class JavassitTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc;
		try {
			cc = pool.get("com.sun.javassit.Run");
			CtMethod ctMethod = cc.getDeclaredMethod("run");
			ctMethod.addParameter(CtClass.longType);
			ctMethod.addLocalVariable("begin", CtClass.longType);
			
			ctMethod.insertBefore(" begin = System.currentTimeMillis(); Thread.sleep(500l); ");
			ctMethod.insertAfter(" System.out.println(\" ����ִ�к�ʱ��\" + (System.currentTimeMillis()-begin)); ");
			cc.writeFile();
			java.security.ProtectionDomain domain = Thread.currentThread().getClass().getProtectionDomain();
			//System.out.println(domain.getPermissions().toString());
			Class<?> c = cc.toClass();
			//Run run = (Run) c.newInstance();
			//run.run();
			Run run = new Run();
			//run.run();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		

	}

}
*/