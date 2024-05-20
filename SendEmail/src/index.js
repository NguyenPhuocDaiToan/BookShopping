const express = require('express');
const nodemailer = require('nodemailer');
const app = express();
const router = express.Router();
const port = 3000;

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.listen(port, () => console.log(`Example app listening on port ${port}!`));


function generateOTP() {
    // Tạo ra một số ngẫu nhiên từ 100000 đến 999999
    const otp = Math.floor(100000 + Math.random() * 900000);
    return otp;
}

router.get('/getOtp', (req, res) => {
    const { email } = req.params;

    const otp = generateOTP();
    const message = "<h3>Cảm ơn bạn đã sử dụng ứng dụng của chúng tôi !!!</h3>"
                + "<p>Mã OTP để thay đổi mật khẩu của bạn là: " + otp + ".</p>"
        + "<p>Mã OTP có hiệu lực trong thời gian 4 phút.</p>";
    
    const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'nguyentoan102002@gmail.com',
            pass: 'huhqlhlixhyvusbe'
        }
    });

    const mailOptions = {
        from: 'nguyentoan102002@gmail.com',
        to: email,
        subject: '[PBL7 BookRecommendation] OTP to change password',
        text: message
    };

    transporter.sendMail(mailOptions, function (error, info) {
        if (error) {
            console.log(error);
            res.json({ message: 'Error' });
        } else {
            console.log('Email sent: ' + info.response);
            res.json({ message: 'Email sent' });
        }
    });
})

router.get('/getOtpRegister', (req, res) => {
    const { email } = req.params;

    const otp = generateOTP();
    const message = "<h3>Cảm ơn bạn đã sử dụng ứng dụng của chúng tôi !!!</h3>"
                + "<p>Mã OTP để thay đổi mật khẩu của bạn là: " + otp + ".</p>"
        + "<p>Mã OTP có hiệu lực trong thời gian 4 phút.</p>";
    
    const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'nguyentoan102002@gmail.com',
            pass: 'huhqlhlixhyvusbe'
        }
    });

    const mailOptions = {
        from: 'nguyentoan102002@gmail.com',
        to: email,
        subject: '[PBL7 BookRecommendation] OTP to change password',
        text: message
    };

    transporter.sendMail(mailOptions, function (error, info) {
        if (error) {
            console.log(error);
            res.json({ message: 'Error' });
        } else {
            console.log('Email sent: ' + info.response);
            res.json({ message: 'Email sent' });
        }
    });
})

app.use('/', router);
