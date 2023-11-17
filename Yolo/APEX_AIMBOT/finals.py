from AimBot import AimBot
import multiprocessing
import time
import os

class ApexAimBot(AimBot):
    def __init__(self, config_path, onnx_path, engine_path):
        super().__init__(config_path, onnx_path, engine_path)

    def initialize_params(self):
        super().initialize_params()
        self.smooth = self.args.smooth * self.args.resolution_y / self.args.resolution_x  # default settings by game


if __name__ == '__main__':
    os.path.abspath(os.path.dirname(__file__))
    multiprocessing.freeze_support()
    apex = ApexAimBot(config_path='configs/thefinals.yaml',
                      onnx_path='weights/thefinals.onnx', engine_path='weights/thefinals.trt')
    # apex_best_2.engine
    while True:
        apex.forward()
