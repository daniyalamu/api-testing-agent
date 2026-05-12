from fastapi import FastAPI
from pydantic import BaseModel
from huggingface_hub import InferenceClient
from dotenv import load_dotenv
import os

load_dotenv()

app = FastAPI()

client = InferenceClient(
    api_key=os.getenv("HF_TOKEN")
)

MODEL = "meta-llama/Llama-3.1-8B-Instruct"

class GenerateRequest(BaseModel):
    endpoint: str
    method: str
    description: str | None = None

@app.get("/")
def home():
    return {
        "message": "Hugging Face AI Service Running"
    }

@app.post("/generate-tests")
def generate_tests(request: GenerateRequest):

    try:

        prompt = f"""
        Generate API test cases for:

        Endpoint: {request.endpoint}
        Method: {request.method}
        Description: {request.description}

        Include:
        - Positive test cases
        - Negative test cases
        - Security test cases
        - Performance test cases

        Return ONLY valid JSON array.
        """

        completion = client.chat.completions.create(
            model=MODEL,
            messages=[
                {
                    "role": "user",
                    "content": prompt
                }
            ],
            max_tokens=500
        )

        return {
            "result": completion.choices[0].message.content
        }

    except Exception as e:
        return {
            "error": str(e)
        }