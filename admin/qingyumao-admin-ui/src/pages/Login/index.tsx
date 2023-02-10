import {login} from '@/services/login/api';

import {
    AlipayCircleOutlined,
    LockOutlined,
    MobileOutlined,
    TaobaoCircleOutlined,
    UserOutlined,
    WeiboCircleOutlined,
} from '@ant-design/icons';
import {
    LoginForm,
    ProFormCaptcha,
    ProFormCheckbox,
    ProFormText,
} from '@ant-design/pro-components';
import {message, Tabs} from 'antd';
import React, {useState} from 'react';
import { history, useModel} from '@umijs/max';
import styles from './index.less';
import {setToken} from "@/utils/token-util";
import { FormattedMessage,useIntl } from 'umi';

const Login: React.FC = () => {

    const [type, setType] = useState<string>('account');
    const {initialState, setInitialState} = useModel('@@initialState');

    const intl = useIntl();



    const handleSubmit = async (values: Login.LoginParams) => {
        // 登录
        const {success, data} = await login({...values, type});
        if (success) {
            const defaultLoginSuccessMessage = intl.formatMessage({
                id: 'pages.login.success',
                defaultMessage: '登录成功！',
            });
            message.success(defaultLoginSuccessMessage);
            setToken(data?.token);
            // await fetchUserInfo();
            /** 此方法会跳转到 redirect 参数所在的位置 */
            if (!history) return;
            const {query} = history.location;
            const {redirect} = query as { redirect: string };
            history.push(redirect || '/');
            return;
        }
    };

    return (
        <div className={styles.container}>
            <div className={styles.content}>
                <LoginForm
                    title="后台管理系统"
                    subTitle={' '}
                    initialValues={{
                        autoLogin: true,
                    }}
                    actions={[
                        <FormattedMessage
                            key="loginWith"
                            id="pages.login.loginWith"
                            defaultMessage="其他登录方式"
                        />,
                        <WeiboCircleOutlined key="WeiboCircleOutlined" className={styles.icon}/>,
                    ]}
                    onFinish={async (values) => {
                        await handleSubmit(values as Login.LoginParams);
                    }}
                >
                    <ProFormText
                        name="username"
                        fieldProps={{
                            size: 'large',
                            prefix: <UserOutlined className={styles.prefixIcon}/>,
                        }}
                        placeholder={intl.formatMessage({
                            id: 'pages.login.username.placeholder',
                            defaultMessage: '用户名: admin or user',
                        })}
                        rules={[
                            {
                                required: true,
                                message: (
                                    <FormattedMessage
                                        id="pages.login.username.required"
                                        defaultMessage="请输入用户名!"
                                    />
                                ),
                            },
                        ]}
                    />
                    <ProFormText.Password
                        name="password"
                        fieldProps={{
                            size: 'large',
                            prefix: <LockOutlined className={styles.prefixIcon}/>,
                        }}
                        placeholder={intl.formatMessage({
                            id: 'pages.login.password.placeholder',
                            defaultMessage: '密码: ant.design',
                        })}
                        rules={[
                            {
                                required: true,
                                message: (
                                    <FormattedMessage
                                        id="pages.login.password.required"
                                        defaultMessage="请输入密码！"
                                    />
                                ),
                            },
                        ]}
                    />
                    <div
                        style={{
                            marginBottom: 24,
                        }}
                    >
                        <ProFormCheckbox noStyle name="autoLogin">
                            <FormattedMessage id="pages.login.rememberMe" defaultMessage="自动登录"/>
                        </ProFormCheckbox>
                        <a
                            style={{
                                float: 'right',
                            }}
                        >
                            <FormattedMessage id="pages.login.forgotPassword" defaultMessage="忘记密码"/>
                        </a>
                    </div>
                </LoginForm>
            </div>

        </div>
    );
};

export default Login;
