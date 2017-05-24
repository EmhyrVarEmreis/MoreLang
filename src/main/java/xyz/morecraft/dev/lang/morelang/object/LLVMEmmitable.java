package xyz.morecraft.dev.lang.morelang.object;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface LLVMEmmitable {

    List<String> llvm(FunctionContextRegistry functionContextRegistry);

}
