package com.youyijia.goodhealth.entity;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/18.
 */
public class SanjiInfo implements IPickerViewData {
    /**
     * children : [{"children":[{"name":"东城区","parent":"110100","value":"110101"},{"name":"西城区","parent":"110100","value":"110102"},{"name":"朝阳区","parent":"110100","value":"110105"},{"name":"丰台区","parent":"110100","value":"110106"},{"name":"石景山区","parent":"110100","value":"110107"},{"name":"海淀区","parent":"110100","value":"110108"},{"name":"门头沟区","parent":"110100","value":"110109"},{"name":"房山区","parent":"110100","value":"110111"},{"name":"通州区","parent":"110100","value":"110112"},{"name":"顺义区","parent":"110100","value":"110113"},{"name":"昌平区","parent":"110100","value":"110114"},{"name":"大兴区","parent":"110100","value":"110115"},{"name":"怀柔区","parent":"110100","value":"110116"},{"name":"平谷区","parent":"110100","value":"110117"},{"name":"密云区","parent":"110100","value":"110118"},{"name":"延庆区","parent":"110100","value":"110119"}],"name":"市辖区","parent":"110000","value":"110100"}]
     * name : 北京市
     * value : 110000
     */

    private String name;
    private String value;
    private List<ChildrenBeanX> children;

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * children : [{"name":"东城区","parent":"110100","value":"110101"},{"name":"西城区","parent":"110100","value":"110102"},{"name":"朝阳区","parent":"110100","value":"110105"},{"name":"丰台区","parent":"110100","value":"110106"},{"name":"石景山区","parent":"110100","value":"110107"},{"name":"海淀区","parent":"110100","value":"110108"},{"name":"门头沟区","parent":"110100","value":"110109"},{"name":"房山区","parent":"110100","value":"110111"},{"name":"通州区","parent":"110100","value":"110112"},{"name":"顺义区","parent":"110100","value":"110113"},{"name":"昌平区","parent":"110100","value":"110114"},{"name":"大兴区","parent":"110100","value":"110115"},{"name":"怀柔区","parent":"110100","value":"110116"},{"name":"平谷区","parent":"110100","value":"110117"},{"name":"密云区","parent":"110100","value":"110118"},{"name":"延庆区","parent":"110100","value":"110119"}]
         * name : 市辖区
         * parent : 110000
         * value : 110100
         */

        private String name;
        private String parent;
        private String value;
        private List<ChildrenBean> children;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * name : 东城区
             * parent : 110100
             * value : 110101
             */

            private String name;
            private String parent;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
