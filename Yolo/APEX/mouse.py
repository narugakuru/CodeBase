import random

from tools.logitech import Logitech


def generate_random_int(lower_bound, upper_bound):
    """
    生成随机整数
    :return: 随机整数
    """
    return random.randint(lower_bound, upper_bound)


def lock(box_list, grab_width, grab_height, pids, offset):
    if not box_list:
        return None

    min_distance_sq = float('inf')
    target_position = None

    half_grab_width = grab_width / 2
    half_grab_height = grab_height / 2

    for aim in box_list:
        cls = int(aim[0])
        if cls == 0:  # 只锁敌人
            box_center_x = float(aim[1]) * grab_width
            box_center_y = float(aim[2]) * grab_height

            distance_sq = (box_center_x - half_grab_width) ** 2 + (box_center_y - half_grab_height) ** 2

            if distance_sq < min_distance_sq:
                min_distance_sq = distance_sq
                target_position = (box_center_x, box_center_y, aim[3], aim[4])

    if target_position:

        box_w = int(float(target_position[2]) * half_grab_width)
        box_h = int(float(target_position[3]) * half_grab_height)

        raw_x = (target_position[0] - half_grab_width)
        raw_y = (target_position[1] - half_grab_height) - int(box_h * offset)

        range_x, range_y = 2, 2

        is_range = abs(raw_x) <= (box_w * range_x) and abs(raw_y) <= (box_h * range_y)  # 鼠标在目标框附近才锁

        # print(f"is_range：{is_range} abs_x：{abs(raw_x)}  abs_y：{abs(raw_y)}  box_w：{box_w * range_x} box_y：{box_h * range_y}")

        if is_range:
            # 添加偏移值

            random_step_x = generate_random_int(6, 12)
            random_step_y = generate_random_int(4, 12)

            _pid_x = int(pids[0].getMove(raw_x, random_step_x))
            _pid_y = int(pids[1].getMove(raw_y, random_step_y))

            Logitech.mouse.move(_pid_x, _pid_y)
