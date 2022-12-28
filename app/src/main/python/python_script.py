import openai

def main(user_prompt):
    openai.api_key = ("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")
    filtered_prompt = str(user_prompt)
    response = openai.Completion.create(model="text-davinci-003", prompt= filtered_prompt, temperature=0, max_tokens=500)

    return response["choices"][0]["text"]
