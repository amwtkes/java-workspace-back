package com.xiaojin.athome.console;

import jdk.dynalink.linker.support.Lookup;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

public class Java8way {
//    private static CallSite cs;
//    public static void main(String args[])
//    {
//        Runnable runnable = invokedynamic# bootstrapLambda, lambda$main$0
//        Thread thread = new Thread(runnable);
//        thread.start();
//    }
//    private static void lambda$main$0()
//    {
//        System.out.println("Am much more shorter");
//    }
//    private static CallSite bootstrapLambda(Lookup lookup, String name, MethodType type)
//    {
//        //lookup = provided by VM
//        //name = "lambda$main$0", provided by VM
//        //type = void
//        MethodHandle lambdaImplementation = lookup.findStatic(lookup.lookupClass(), name, type);
//        return LambdaMetafactory.metafactory(lookup,
//                "run",
//                MethodType.methodType(Runnable.class), //signature of lambda factory
//                MethodType.methodType(void.class), //signature of method Consumer.accept after type erasure
//                lambdaImplementation, //reference to method with lambda body
//                type);
//    }
}
