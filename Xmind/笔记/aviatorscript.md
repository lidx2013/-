### @Import

导入Java类代码，脚本可以直接使用

```
@Import(ns = "str")
  public static class StringModule {
    public static boolean isBlank(final String s) {
      System.out.println("1111");
      return s == null || s.trim().length() == 0;
    }
  }
  
String script = "let str = require('str'); str.isBlank(s,b) ";

System.out.println(AviatorEvaluator.execute(script, AviatorEvaluator.newEnv("s","hello","b", "323")));
```

### Invocable  执行脚本函数

```
ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEngineByName("AviatorScript");
String script = "fn hello(name) { print('Hello222, ' + name); }";
engine.eval(script);
Invocable inv = (Invocable) engine;
inv.invokeFunction("hello", "Scripting!!")
```