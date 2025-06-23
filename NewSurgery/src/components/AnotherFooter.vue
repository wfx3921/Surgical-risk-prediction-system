<template>
    <div class="footer">
        <div class="footer-main">

            <div class="footer-right">
                <div class="unit-text">
                    <span class="unit-label">平台技术支撑单位</span>
                    <span class="unit-label-en">Platform Technical Support Unit</span>
                </div>
                <img src="../assets/images/OUC_block.png" alt="平台技术支撑单位" class="footer-logo">
            </div>
            
        </div>
        <div class="footer-copyright">
            <span>Copyright © 2024 术前风险评估平台 All Rights Reserved</span>
        </div>
    </div>
</template>

<script>
export default {
    name: 'FooterComponent',
};
</script>

<style scoped>
.footer {
    background-color: #2c3e50;
    padding: 8px 0;
    color: #ffffff;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.footer-main {
    width: 100%;
    display: flex;
    justify-content: center;
    padding: 0;
    margin-bottom: 3px;
}


.footer-right {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-left: 50px;
}

.unit-text {
    display: flex;
    flex-direction: column;
    margin-right: 10px;
    text-align: center;
}

.unit-label {
    font-size: 16px;
    color: #ffffff;
}

.unit-label-en {
    font-size: 10px;
    color: rgba(255, 255, 255, 0.8);
}

.footer-logo {
    height: 35px;
}

.footer-contact {
    display: flex;
    flex-direction: column;
    min-width: 220px;
    text-align: center;
    margin-left: 50px;
}

.footer-contact span {
    color: rgba(255, 255, 255, 0.8);
    font-size: 13px;
    line-height: 1.5;
}

.footer-copyright {
    width: 100%;
    text-align: center;
    margin: 15px 0 0 0;
}

.footer-copyright span {
    color: rgba(255, 255, 255, 0.8);
    font-size: 13px;
}
</style>
