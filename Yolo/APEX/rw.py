import configparser

_config_bit = configparser.ConfigParser()
# 从1bit.ai.config文件中读取参数和值
textGroup = 'group'  # 分组名称


def read_file():
    try:
        # 创建 ConfigParser 对象
        config = configparser.ConfigParser()

        # 读取配置文件内容
        config.read('1bit.ai.config')

        # 获取指定分组的所有键值对
        items = config.items(textGroup)

        return items
    except Exception as e:
        print(f"读取配置文件出错：{e}")
        return []


def _write_file():
    with open("1bit.ai.config", "w") as f:
        _config_bit.write(f)
        print('文件已写修改成功 请查看 1bit.ai.config')
    pass


def write_key_value(key, value):
    value = str(value)  # 存入统一转字符串
    # 检查 textGroup 是否在 config 字典中
    if textGroup not in _config_bit:
        # 如果 textGroup 不存在，创建一个新的空字典
        _config_bit.setdefault(textGroup, {})
    # 将 key 和 value 存储到字典中
    _config_bit[textGroup][key] = value


def read_key(key):
    try:
        value = _config_bit[textGroup][key]
    except KeyError:
        print(f"KeyError: cannot find value for key '{key}'")
        value = 0  # 返回 0 表示没有找到对应的值
    return value


def convert(str_val):
    # 如果传入的是布尔值，则直接使用 bool 函数转换
    if str_val.lower() == "true":
        return True
    elif str_val.lower() == "false":
        return False
    # 尝试转换为整数或浮点数
    try:
        val = int(str_val)
    except ValueError:
        try:
            val = float(str_val)
        except ValueError:
            val = str_val  # 如果转换失败，则返回原字符串

    return val
