```markdown
# 手术风险预测系统（Surgical Risk Prediction System）

> 本项目旨在基于 XGBoost 模型与 Spring Boot 构建一套完整的手术风险预测平台，包含前端可视化界面、后端 RESTful API 服务、预测模型组件及数据库脚本。通过输入患者术前各项指标，系统自动输出手术风险评分，并提供用户与管理员权限管理。

---

## 目录结构

```
```
项目根目录/
├── NewSurgery/                      # 前端代码（Vue.js）
│   ├── public/                      # 公共静态资源
│   ├── src/                         # vue 源码
│   └── vue.config.js
├── Surgical\_Risk\_Prediction/      # 后端代码（Spring Boot + MyBatis-Plus + Redis + Spring Security）
│   ├── src/
│   │   ├── main/
│   │   │   ├── java\com\ouc\riskprediction    # Java 源码
│   │   │   │   ├── config/          # 安全、Redis 等配置
│   │   │   │   ├── controller/      # 控制器层（REST API）
│   │   │   │   ├── service/         # 服务层
                .
                .
                .
│   │   │   ├── resources/
│   │   │   │   └── application.yml  # 配置文件
│   └── pom.xml                      # Maven 配置
├── pythonProject/                   # XGBoost 预测模型
├── Surgery.sql                      # SQL 脚本：手术风险预测 **输出** 值表结构及示例数据
└── surgical\_risk\_prediction.sql   # SQL 脚本：手术风险预测 **输入** 值表结构、用户与权限管理表结构及示例数据
```

---

## 一、功能概览

1. **用户管理**  
   - 普通用户和管理员角色  
   - 基于 Spring Security + JWT 实现身份验证与权限控制
2. **术前指标录入**  
   - 支持单条录入患者基本信息和各项医学指标
3. **风险预测**  
   - 调用 Python XGBoost 模型，对录入的指标数据进行风险评分  
   - 实时返回风险值及风险等级（低/中/高）
4. **结果展示**  
   - 前端可视化图表展示历史预测结果
5. **系统监控**  
   - Redis 缓存常用查询，加速接口响应  
   - 日志收集与简单的健康检查接口

---

## 二、快速开始

### 1. 环境要求

- **前端**  
  - Node.js ≥ 16.x  
  - npm 或 yarn  
- **后端**  
  - JDK 1.8+  
  - Maven 3.6+  
  - Redis 5.0+  
  - MySQL 5.7+ / MariaDB  
- **模型服务**  
  - Python 3.7+  
  - XGBoost

### 2. 数据库初始化

1. 在 MySQL 中创建数据库：
   ```sql
   CREATE DATABASE surgical_risk CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

2. 执行表结构与示例数据：

   ```bash
   mysql -u root -p surgical_risk < surgical_risk_prediction.sql
   mysql -u root -p surgical_risk < Surgery.sql
   ```

### 3. 后端启动

1. 进入后端项目根目录：

   ```bash
   cd Surgical_Risk_Prediction
   ```
2. 修改 `src/main/resources/application.yml` 中的数据库和 Redis 配置：

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/surgical_risk?useUnicode=true&characterEncoding=utf8
       username: root
       password: your_password
   redis:
     host: localhost
     port: 6379
   jwt:
     secret: your_jwt_secret
     expiration: 3600
   ```
3. 构建并运行：

   ```bash
   mvn clean package -DskipTests
   java -jar target/Surgical_Risk_Prediction-0.0.1-SNAPSHOT.jar
   ```
4. 默认监听端口：`8080`

### 4. 模型服务启动

> 后端通过进程调用或 REST 接口联动 Python 脚本做预测。

1. 进入模型项目目录：

   ```bash
   cd pythonProject
   ```
2. 安装依赖：

   ```bash
   pip install -r requirements.txt
   ```
3. 测试预测脚本：

   ```bash
   python src/predict.py --input example_input.json --output result.json
   ```

### 5. 前端启动

1. 进入前端项目目录：

   ```bash
   cd NewSurgery
   ```
2. 安装依赖：

   ```bash
   npm install
   ```
3. 本地开发运行：

   ```bash
   npm run serve
   ```
4. 访问地址：`http://localhost:8081`

---

## 三、API 文档示例

| 模块        | 接口                   | 方法   | 说明              |
| --------- | -------------------- | ---- | --------------- |
| 用户认证      | `/api/auth/login`    | POST | 登录，返回 JWT token |
|           | `/api/auth/register` | POST | 注册新用户           |
| 风险预测      | `/api/risk/predict`  | POST | 提交指标，返回风险评分与等级  |
| 历史记录      | `/api/risk/history`  | GET  | 获取当前用户预测历史      |
| 用户管理（管理员） | `/api/users`         | GET  | 列表所有用户          |
|           | `/api/users/{id}`    | PUT  | 修改用户信息与角色       |

---

## 四、默认账号信息

* **普通用户**

    * 用户名：`RiskAssessMD`
    * 密码：`qwer20250402`
* **管理员**

    * 用户名：`RiskAssessAdmin`
    * 密码：`qwer20250402`

> 强烈建议部署前修改初始用户名和密码。

---


## 五、常见问题（FAQ）

1. **模型预测速度慢**

    * 确认 Python 环境与依赖版本一致，模型文件是否预加载。
    * 可使用 `joblib` 将模型序列化为二进制加速加载。
2. **前端跨域问题？**

    * 在后端 `CorsConfig` 中开启 `@CrossOrigin` 或全局 CORS 配置。
3. **Redis 缓存未生效？**

    * 检查 `application.yml` 中的 Redis 连接配置是否正确。
    * 确保本地或服务器 Redis 服务已启动并开放端口。



