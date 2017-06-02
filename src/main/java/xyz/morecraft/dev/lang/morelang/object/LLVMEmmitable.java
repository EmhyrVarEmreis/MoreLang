package xyz.morecraft.dev.lang.morelang.object;

import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;

import java.util.List;

public interface LLVMEmmitable {

    List<String> llvm(FunctionContextRegistry functionContextRegistry);

}
