import os

# for scraping new bookings
kth_username = os.getenv('KTH_USERNAME')
kth_password = os.getenv('KTH_PASSWORD')

# for sending email
EMAIL_USERNAME = os.getenv('EMAIL_USERNAME')
EMAIL_PASSWORD = os.getenv('EMAIL_PASSWORD')

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException
import smtplib
from email.mime.text import MIMEText

def send_email(subject, body):
    SMTP_SERVER = "smtp.gmail.com"
    SMTP_PORT = 465
    USERNAME = EMAIL_USERNAME
    PASSWORD = EMAIL_PASSWORD # ylvtpjnneffjitxk

    sender_email = USERNAME
    receiver_email = "dad@datasektionen.se"

    message = MIMEText(body, "plain")
    message["Subject"] = subject
    message["From"] = sender_email
    message["To"] = receiver_email

    with smtplib.SMTP_SSL(SMTP_SERVER, SMTP_PORT) as server:
        server.login(USERNAME, PASSWORD)  # Log in to SMTP server
        server.sendmail(sender_email, receiver_email, message.as_string())  # Send email
        print("Email sent successfully!")

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

driver.get("https://bokning.datasektionen.se/admin/bookings")

wait = WebDriverWait(driver, 20)

username_input = wait.until(EC.presence_of_element_located((By.ID, "userNameInput")))

username_input.send_keys(kth_username)
next_button = driver.find_element(By.ID, "nextButton")
next_button.click()

wait = WebDriverWait(driver, 10)

password_input = wait.until(EC.presence_of_element_located((By.ID, "passwordInput")))
password_input.send_keys(kth_password)

submit_button = driver.find_element(By.ID, "submitButton")
submit_button.click()

wait.until(EC.url_contains("/admin/bookings"))

print("Logged in, current page title:", driver.title)

try:
    no_booking_text = "Du har inga bokningar att hantera! Bra jobbat!"
    p_element = driver.find_element(By.XPATH, f"//p[contains(text(), '{no_booking_text}')]")
    send_email("Inga nya bokningar!", "Det finns inga nya bokningar att administrera. ")
except NoSuchElementException:
    send_email("Nya bokningsförfrågningar!", """
                                        Det finns nya bokningsförfrågningar för DAD! 
                                        Logga in på bokning.datasektionen.se för att administrera bokningarna.
                                        """)

driver.quit()