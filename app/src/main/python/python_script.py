import openai

def main(user_prompt):
    openai.api_key = ("API_KEY")
    filtered_prompt = str(user_prompt)
    try:
        response = openai.Completion.create(model="text-davinci-003", prompt= filtered_prompt, temperature=0, max_tokens=500)
    except:
        return "ChatGPT had an error"
    else:
        return response["choices"][0]["text"]
