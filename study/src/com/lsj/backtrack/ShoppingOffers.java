package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 在LeetCode商店中， 有许多在售的物品。
 *
 * 然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 *
 * 现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
 *
 * 每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。
 *
 * 任意大礼包可无限次购买。
 *
 * 示例 1:
 *
 * 输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * 输出: 14
 * 解释:
 * 有A和B两种物品，价格分别为¥2和¥5。
 * 大礼包1，你可以以¥5的价格购买3A和0B。
 * 大礼包2， 你可以以¥10的价格购买1A和2B。
 * 你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
 * 示例 2:
 *
 * 输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * 输出: 11
 * 解释:
 * A，B，C的价格分别为¥2，¥3，¥4.
 * 你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。
 * 你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
 * 你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
 * 说明:
 *
 * 最多6种物品， 100种大礼包。
 * 每种物品，你最多只需要购买6个。
 * 你不可以购买超出待购清单的物品，即使更便宜。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shopping-offers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ShoppingOffers {

    public int ans = 0;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (price == null || price.size() == 0) {
            return 0;
        }
        for (int i = 0; i < needs.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }
        helper(price, special, needs, 0, 0);
        return ans;
    }

    private void helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
                       int step, int amount) {
        // 递归终止条件 尝试完所有的套餐后
        if (step >= special.size()) {
            for (int i = 0; i < needs.size(); i++) {
                // 不能再买套餐了，剩下的只能单个买
                amount += needs.get(i) * price.get(i);
            }
            // 如果这种买法更便宜，那就取这种买法的价格
            ans = Math.min(ans, amount);
            return;
        }
        List<Integer> currentSpecial = special.get(step);
        int currentSpecialSize = currentSpecial.size();
        Integer currentSpecialMoney = currentSpecial.get(currentSpecialSize - 1);
        int currentSpecialMaxNum = Integer.MAX_VALUE;
        // 判断一下当前套餐 最多能买多少个
        for (int i = 0; i < needs.size(); i++) {
            Integer need = needs.get(i);
            Integer current = currentSpecial.get(i);
            if (current > 0) {
                currentSpecialMaxNum = Math.min(currentSpecialMaxNum, need / current);
            }
        }

        for (int i = 0; i <= currentSpecialMaxNum; i++) {
            // 开始做选择前，先把需要买的东西复制出来，因为每次做选择最终要做的事都是一样的
            List<Integer> temp = new ArrayList<>(needs);
            // 开始做选择，每次做完选择后，需要做的事情都会变
            for (int j = 0; j < needs.size(); j++) {
                // 进入这里，说明你做了一次选择，买了i个礼包，买完i个礼包后，你需要买的东西会减少
                needs.set(j, needs.get(j) - currentSpecial.get(j) * i);
            }
            // 买了i个当前礼包，如果做这个选择之后的金额已经大于之前算出来的金额了，就没必要继续做选择了，因为这条路的金额肯定大于之前的
            if (amount + i * currentSpecialMoney < ans) {
                helper(price, special, needs, step + 1, amount + i * currentSpecialMoney);
            }
            // 回溯，还原做选择前的目标
            needs = temp;
        }

    }
}
