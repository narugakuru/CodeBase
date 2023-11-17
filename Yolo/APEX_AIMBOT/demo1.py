import tensorrt as trt
import pycuda.driver as cuda
import pycuda.autoinit

TRT_LOGGER = trt.Logger(trt.Logger.WARNING)
builder = trt.Builder(TRT_LOGGER)

onnx_model_path = "path/to/your/onnx/model.onnx"
with open(onnx_model_path, 'rb') as f:
    onnx_data = f.read()

network_flags = 1 << int(trt.NetworkDefinitionCreationFlag.EXPLICIT_BATCH)
network = builder.create_network(flags=network_flags)

parser = trt.OnnxParser(network, TRT_LOGGER)

success = parser.parse(onnx_data)
if not success:
    for error in range(parser.num_errors):
        print(parser.get_error(error))
    raise RuntimeError("Failed to parse the ONNX model.")

builder.max_batch_size = 1  # 设置最大批处理大小
builder.max_workspace_size = 1 << 30  # 设置最大工作空间大小（可以根据需要进行调整）
engine = builder.build_cuda_engine(network)

engine_path = "path/to/save/engine.trt"
with open(engine_path, 'wb') as f:
    f.write(engine.serialize())
    
    
# trt6版本
    
import os
import tensorrt as trt
import sys

TRT_LOGGER = trt.Logger()
model_path = 'vgg16.onnx'
engine_file_path = "vgg16.trt"
# EXPLICIT_BATCH = 1 << (int)(trt.NetworkDefinitionCreationFlag.EXPLICIT_BATCH)  # batchsize=1
EXPLICIT_BATCH = []

# with trt.Builder(TRT_LOGGER) as builder, builder.create_network(EXPLICIT_BATCH) \
#        as network, trt.OnnxParser(network, TRT_LOGGER) as parser:
with trt.Builder(TRT_LOGGER) as builder, builder.create_network(*EXPLICIT_BATCH) as network, trt.OnnxParser(network,
                                                                                                            TRT_LOGGER) as parser:
    builder.max_workspace_size = 1 << 28
    builder.max_batch_size = 1
    print(network)
    if not os.path.exists(model_path):
        print('ONNX file {} not found.'.format(model_path))
        exit(0)
    print('Loading ONNX file from path {}...'.format(model_path))
    with open(model_path, 'rb') as model:
        print('Beginning ONNX file parsing')
        if not parser.parse(model.read()):
            print('ERROR: Failed to parse the ONNX file.')
            for error in range(parser.num_errors):
                print('parser.get_error(error)', parser.get_error(error))

    last_layer = network.get_layer(network.num_layers - 1)
    network.mark_output(last_layer.get_output(0))

    network.get_input(0).shape = [1, 3, 640, 160]
    print('Completed parsing of ONNX file')
    engine = builder.build_cuda_engine(network)
    with open(engine_file_path, "wb") as f:
        f.write(engine.serialize())
        print('save  trt success!!')
